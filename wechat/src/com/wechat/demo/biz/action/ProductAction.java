package com.wechat.demo.biz.action;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wechat.demo.dto.ProductInfoFormDTO;

@Controller
public class ProductAction {
	@RequestMapping(method = RequestMethod.POST,value="/docc/demo/saveProductInfo.do")
	@ResponseBody//用来保证返回的不是view，如果没有这个，总是会寻找view返回
	public void saveProductInfo(@RequestBody ProductInfoFormDTO formDTO){
		System.out.println("productDTO.name = "+formDTO.getProductDTO().getName());
		System.out.println("userDTO.naem =  "+formDTO.getUserDTO().getName());
	}
}
