package com.leyou.cart.service;

import com.leyou.auth.entity.UserInfo;
import com.leyou.cart.client.GoodsClient;
import com.leyou.cart.interceptor.LoginInterceptor;
import com.leyou.cart.pojo.Cart;
import com.leyou.common.utils.JsonUtils;
import com.leyou.item.pojo.Sku;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private GoodsClient goodsClient;

    static final String KEY_PREFIX = "leyou:cart:uid";
    static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    /**
     * 添加购物车
     *
     * @param cart
     */
    public void addCart(Cart cart) {
        // 先查询之前的购物车数据
        // 获取登录用户
        UserInfo user = LoginInterceptor.getLoginUser();
        // Redis的key
        String key = KEY_PREFIX + user.getId();
        // 获取hash操作对象
        BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(key);

        Long skuId = cart.getSkuId();
        Integer num = cart.getNum();
        // 查询是否存在
        Boolean bool = hashOps.hasKey(skuId.toString());
        if (bool) {
            // 存在，更新购物车数据
            String cartJson = hashOps.get(skuId.toString()).toString();
            cart = JsonUtils.parse(cartJson, Cart.class);
            // 修改购物车数量
            cart.setNum(cart.getNum() + num);   //??
        } else {
            // 不存在，新增购物车数据
            cart.setUserId(user.getId());
            // 其它商品信息，需要查询商品服务
            Sku sku = this.goodsClient.querySkuById(skuId);
            cart.setImage(StringUtils.isBlank(sku.getImages()) ? "" : StringUtils.split(sku.getImages(), ",")[0]);
            cart.setPrice(sku.getPrice());
            cart.setTitle(sku.getTitle());
        }

        // 将购物车数据写入redis
        hashOps.put(cart.getSkuId().toString(), JsonUtils.serialize(cart));
    }

    /**
     * 查询购物车列表
     *
     * @return
     */
    public List<Cart> queryCartList() {
        // 获取登录用户
        UserInfo user = LoginInterceptor.getLoginUser();
        // 判断用户是否有购物车
        String key = KEY_PREFIX + user.getId();
        if (!this.redisTemplate.hasKey(key)) {
            // 没有，直接返回
            return null;
        }
        BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(key);
        List<Object> cartJson = hashOps.values();
        // 判断是否有数据
        if (CollectionUtils.isEmpty(cartJson)) {
            return null;
        }
        // 查询购物车数据
        // 把List<Object>集合转化为List<Cart>集合
        return cartJson.stream().map(o -> JsonUtils.parse(o.toString(), Cart.class)).collect(Collectors.toList());
    }

    /**
     * 修改商品数量
     *
     * @param cart
     */
    public void updateCarts(Cart cart) {
        // 获取登陆用户
        UserInfo user = LoginInterceptor.getLoginUser();
        // 获取hash操作对象
        String key = KEY_PREFIX + user.getId();
        BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(key);
        // 获取购物车信息
        String json = hashOps.get(cart.getSkuId().toString()).toString();
        Cart cart1 = JsonUtils.parse(json, Cart.class);
        // 更新数量
        cart1.setNum(cart.getNum());
        // 写入购物车
        hashOps.put(cart.getSkuId().toString(), JsonUtils.serialize(cart1));
    }

    /**
     * 删除购物车商品
     *
     * @param skuId
     */
    public void deleteCart(String skuId) {
        // 获取登录用户
        UserInfo user = LoginInterceptor.getLoginUser();
        String key = KEY_PREFIX + user.getId();
        BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(key);
        hashOps.delete(skuId);
    }


}
