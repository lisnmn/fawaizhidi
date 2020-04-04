package com.sanyouseki.fwzd;

import com.sanyouseki.fwzd.service.impl.ImageServiceImpl;
import com.sanyouseki.fwzd.service.impl.UserServiceImpl;
import net.coobird.thumbnailator.Thumbnails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {
    @Autowired
    ImageServiceImpl imageService;

    @Autowired
    UserServiceImpl userService;

    @Test
    public void test() throws IOException {
        //String url = imageService.getImageById(24).getUrl();
        //System.out.println(url.substring(url.lastIndexOf('/') + 1, url.lastIndexOf('.')));
    }

}
