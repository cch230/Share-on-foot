package com.example.shareonfoot

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jsoup.Jsoup
import org.jsoup.select.Elements

class crawling : AppCompatActivity() {

    var baseUrl = "http://www.gaenso.com/shop/shopbrand.html?type=Y&xcode=062&sort=&page="
    //1 페이지 부터
    var pages = 1
    //3 페이지 까지
    var maxPages = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crawling)

        while (pages <= maxPages){
            var t = Thread(UrlRun(baseUrl,pages,applicationContext))
            //thread 실행
            t.start()
            //t thread가 끝날 때 까지 main thread 대기
            t.join()
            //다음 page 크롤링
            pages++
        }

    }
    class UrlRun(var baseUrl : String, var pages : Int,var context: Context) : Runnable{
        lateinit var elements: Elements
        @Synchronized
        override fun run() {
            try{
                var doc = Jsoup.connect(baseUrl+pages).get()
                elements = doc.select("div.item-cont ul li dl dt a")
                //url들을 room에 저장
                for( e in elements){
                    var url = e.absUrl("href")
                }
            }catch (e : Exception){
            }
        }

    }
}


