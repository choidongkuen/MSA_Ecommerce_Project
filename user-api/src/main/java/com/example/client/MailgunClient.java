package com.example.client;

import com.example.mailgun.SendMailForm;
import org.apache.coyote.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "mailgun", url = "https://api.mailgun.net/v3/")
public interface MailgunClient {

    @PostMapping("sandbox92a3501de81b4fd894cc45108c2e5e8a.mailgun.org/messages")
    Response sendEmail(@SpringQueryMap SendMailForm form);


}
