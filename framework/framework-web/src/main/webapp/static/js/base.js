function post(url,data,fn){
	 var obj = new XMLHttpRequest();
     obj.open("POST", url, true);
     obj.setRequestHeader("Content-type", "application/x-www-form-urlencoded");  // 添加http头，发送信息至服务器时内容编码类型
     obj.onreadystatechange = function() {
         if (obj.readyState == 4 && (obj.status == 200 || obj.status == 304)) {  // 304未修改
             fn(obj.responseText);
         }
     };
     obj.send(data);
}