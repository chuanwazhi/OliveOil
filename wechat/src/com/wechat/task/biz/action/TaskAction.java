package com.wechat.task.biz.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TaskAction {
	
	@RequestMapping(method = RequestMethod.POST,value="/docc/publishTask.do")
	@ResponseBody//用来保证返回的不是view，如果没有这个，总是会寻找view返回
	//js传过来的是json字符串，所以要用String接收，然后转换成需要的对象
//	public Map<String,String> publishTask3( @RequestBody Map<String,Object> params){
//	public  Map<String,String> publishTask3( @RequestParam("insureBuyPhone")  String insureBuyPhone){
	public  Map<String,String> publishTask3( @RequestBody ADTO params){
		//jquery的json请求，使用@RequestParam("insureBuyPhone")  String insureBuyPhone接收,只能接受String，map不行，DTO还没有尝试
		//angularJS的json请求，使用@RequestBody Map<String,Object> params接收，原因未知
		System.out.println("aaaaaaaa"+params.getInsureBuyPhone());
//		System.out.println("323232333"+insureBuyPhone);.get("insureBuyPhone")
		Map aa = new HashMap<String, String>();
		aa.put("aa", "12321");
		return aa;
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/docc/publishTask31.do")
	@ResponseBody//用来保证返回的不是view，如果没有这个，总是会寻找view返回
	//js传过来的是json字符串，所以要用String接收，然后转换成需要的对象
//	public Map<String,String> publishTask3( @RequestBody Map<String,Object> params){
	public  Map<String,String> publishTask31( @RequestParam("aa")  String insureBuyPhone){
		//jquery的json请求，使用@RequestParam("insureBuyPhone")  String insureBuyPhone接收,只能接受String，map不行，DTO还没有尝试
		//angularJS的json请求，使用@RequestBody Map<String,Object> params接收，原因未知
		System.out.println("vvvvvvv"+insureBuyPhone);
//		System.out.println("323232333"+insureBuyPhone);.get("insureBuyPhone")
		Map aa = new HashMap<String, String>();
		aa.put("aa", "12321");
		return aa;
	}
}
