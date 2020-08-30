package com.leyou.upload.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * 导入FastDFS-Client组件
 *
 * @author fanor
 */
@Configuration
@Import(FdfsClientConfig.class)
// 解决jmx重复注册bean的问题
//@EnableMBeanExport(registration = RegistrationPolicy.FAIL_ON_EXISTING)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FastClientImporter {

}
