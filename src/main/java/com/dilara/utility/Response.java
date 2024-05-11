package com.dilara.utility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response<T> {
    /**
     * 200 -> Başarılı
     * 400 -> Başarısız, kullanıcı kaynaklı
     * 500 -> Başarısız, program kaynaklı
     */
    int statusCode;
    /**
     * Başarılı ise olumlu mesajlar
     * Başarısız ise nedenini açıklayan mesajlar
     */
    String message;
    /**
     * Eğer başarılı bir şekilde sonlanmış ise kullanıcıya iletilecek bilgi datası
     */
    T data;

}
