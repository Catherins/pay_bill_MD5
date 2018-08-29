1.需要修改src下面的config.properties中的配置
	1.1 mch_id：商户号
	1.2 key：MD5签名交易密钥
	1.3 req_url：支付请求url
	1.4 notify_url：支付成功商户接收异步回调通知url
	1.5 mchPrivateKey:RSA签名商户私钥
	1.6 platPublicKey:RSA签名平台公钥
2.本项目没有记录文件或者数据库日志，只是记录在容器日志里面，如有需要可以将System.out.println类似的地方改写成log文件或者数据库

