package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Sean
 * @email wanghaitao21@aisino.com
 * @create_time 2023-08-16 01:05
 */
public interface OssService {

    String uploadFileAvatar(MultipartFile file);
}
