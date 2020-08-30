package com.leyou.cart.controller;

import com.leyou.cart.pojo.Cart;
import com.leyou.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class CartController {
    @Autowired
    private CartService cartService;

    /**
     * 添加购物车
     *
     * @param cart
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> addCart(@RequestBody Cart cart) {
        this.cartService.addCart(cart);
        return ResponseEntity.ok().build();
    }

    /**
     * 查询购物车列表
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Cart>> queryCartList() {
        List<Cart> carts = this.cartService.queryCartList();
        if (carts == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // 404
        }
        return ResponseEntity.ok(carts);
    }

    /**
     * 修改商品数量
     *
     * @param cart
     */
    @PutMapping
    public ResponseEntity<Void> updateCarts(@RequestBody Cart cart) {
        this.cartService.updateCarts(cart);
        return ResponseEntity.noContent().build();  //204
    }

    /**
     * 删除购物车商品
     *
     * @param skuId
     * @return
     */
    @DeleteMapping("{skuId}")
    public ResponseEntity<Void> deleteCart(@PathVariable("skuId") String skuId) {
        this.cartService.deleteCart(skuId);
        return ResponseEntity.ok().build();
    }

    /**
     * 合并购物车
     *
     * @param cart
     * @return
     */
    @PutMapping("add")
    public ResponseEntity<Void> addCartToUser(@RequestBody Cart cart) {
        this.cartService.addCart(cart);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
