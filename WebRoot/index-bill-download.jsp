<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	<title>支付测试页面</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/index.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript">
		
		function getCurrentDate(){
			var date = new Date();
			return date.getFullYear() + '' + formatString(date.getMonth() + 1) + formatString(date.getDay()) + formatString(date.getHours()) + formatString(date.getMinutes()) + formatString(date.getSeconds()); 
		}
		function formatString(value){
			if(parseInt(value) < 10){
				return  0 + '' + value; 
			}
			return value;
		}
		
		function doSubmit(){
			var service = $.trim($('input[name=service]').val());
			if(service == ''){
				alert('接口类型不能为空');
				return false;
			}
			var bill_date = $.trim($('input[name=bill_date]').val());
			if(bill_date == ''){
				alert('账单日期不能为空');   
				return false; 
			}
			var bill_type = $.trim($('input[name=bill_type]').val());
			if(bill_type == ''){
				alert('账单类型不能为空');
				return false;
			}
			
			$('form').submit();
		}
	</script>
</head>
<body text=#000000 bgColor="#ffffff" leftMargin=0  topMargin=4>
	<div id="main">
        <div class="cashier-nav">
            <ol>
				<li class="current">下载对账单测试 </li> 
            </ol>
        </div>
        <form action="testBillDownload" method="post"  target="_blank">
            <div id="body" style="clear:left">
                <dl class="content">
                    <dt class="hideClass">接口类型：</dt>
					<dd class="hideClass">
						<input name="service" value="pay.bill.merchant" readonly="readonly" maxlength="32"  placeholder="长度32"/>
						<span class="null-star">(长度32)*</span>
						<span></span>
					</dd>
                    <dt>账单日期：</dt>
                    <dd>
                        <span class="null-star"></span>
                        <input name="bill_date" value="20160625" maxlength="32" size="30"  placeholder="长度32"/>
                        <span class="null-star">(长度8)*</span>
                        <span></span>
                    </dd>
                    <dt>账单类型：</dt>
                    <dd>
                        <span class="null-star"></span>
                        <input name="bill_type" value="ALL" maxlength="127" size="30"  placeholder="长度127"/>
                        <span class="null-star">(长度8)*</span>
                        <span></span>
                    </dd>
                    <dt>签名方式：</dt>
                    <dd>
                        <span class="null-star"></span>
                        <input type="radio" name="sign_type" value="RSA_1_256" checked/>RSA_1_256
						<input type="radio" name="sign_type" value="MD5" />MD5
                        <span></span>
                    </dd>
                    <dd>
                        <span class="new-btn-login-sp">
                            <button class="new-btn-login" type="button" onclick="doSubmit()" style="text-align:center;">确 认</button>
                        </span>
                    </dd>
                </dl>
            </div>
		</form>
        <div id="foot">
			<ul class="foot-ul">
				<li><font class="note-help">如果您点击“确认”按钮，即表示您同意该次的执行操作。 </font></li>
				
			</ul>
		</div>
	</div>
</body>
</html>