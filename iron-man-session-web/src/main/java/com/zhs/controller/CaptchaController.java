package com.zhs.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.zhs.utils.Contants;
import com.zhs.vo.CaptchaImageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/8 16:37
 * @package: com.zhs.controller
 * @description:
 */

@RestController
public class CaptchaController {


    @Autowired
    private DefaultKaptcha captchaProducer;



    @GetMapping("/kaptcha")
    public void kaptcha(HttpSession session, HttpServletResponse response) throws IOException{

        response.setDateHeader("Expores",0);
        response.setHeader("Cache-Control", "no-store, no-cache");
        String text = captchaProducer.createText();
        session.setAttribute(Contants.KAPTCHA_SESSION_CODE,new CaptchaImageVo(text,2*60));

            try(ServletOutputStream outputStream = response.getOutputStream();){
                BufferedImage image = captchaProducer.createImage(text);
                ImageIO.write(image,"jpg",outputStream);
                outputStream.flush();
            }
    }


}
