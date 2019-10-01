# acme.md
Tuesday, October 1st 2019, 19:35

# 安装

```shell
curl  https://get.acme.sh | sh
```

# 配置证书

1. 注册域名
2. 生成证书
    - `sudo ~/.acme.sh/acme.sh --issue -d mydomain.me --standalone -k ec-256 --force`, 需要使用80端口
    - 使用nginx时: `sudo ~/.acme.sh/acme.sh --issue -d mydomain.me --nginx -k ec-256 --force`
3. 更新证书
    - `sudo ~/.acme.sh/acme.sh --renew -d mydomain.com --force --ecc`
4. 安装证书
    - `sudo ./acme.sh  --installcert  -d  <domain>.com   \
        --key-file   /etc/nginx/ssl/<domain>.key \
        --fullchain-file /etc/nginx/ssl/fullchain.cer --ecc --force`
5. 配置nginx
    - ```
        server {
              listen  443 ssl;
              ssl on;
              ssl_certificate       /etc/nginx/fullchain.cer;
              ssl_certificate_key   /etc/nginx/ssl/<domain>.key;
              ssl_protocols         TLSv1 TLSv1.1 TLSv1.2;
              ssl_ciphers           HIGH:!aNULL:!MD5;
              server_name           localhost;

              ...
      ```
