package com.assignment.alkyeassignment.utils

import android.app.Activity
import android.os.Handler
import android.os.Looper
import com.assignment.alkyeassignment.R

object Utility {

    fun startTimer(time: Long, timerEnd: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            timerEnd.invoke()
        }, time)
    }

    val tabArr: List<Pair<String, String>>
        get() = listOf(
            Pair("Intro", "Welcome to our latest demonstration of cutting-edge technology! In this demo, we will explore the innovative features and capabilities that set our product apart. Whether you’re a developer, a business leader, or simply a tech enthusiast, you’ll find valuable insights into how our solution addresses common challenges and streamlines processes. From intuitive user interfaces to robust performance metrics, join us as we delve into the core functionalities that make our offering a game-changer in the industry. Get ready to experience the future of [technology/product/solution] and see firsthand how it can transform your [business/workflow/experience]."), Pair(
                "Simulating the enterprise","In today’s digital age, social media has become an omnipresent force, influencing various aspects of our lives from communication to entertainment. Platforms like Facebook, Instagram, and Twitter have redefined how we connect with others and share our experiences. As social media continues to evolve and integrate into daily routines, its impact on mental health has garnered significant attention from researchers, practitioners, and the public alike. While social media offers unprecedented opportunities for connectivity and self-expression, it also presents potential risks that can affect emotional well-being. This multifaceted relationship between social media and mental health necessitates a closer examination of both its positive and negative effects.\n" +
                        "\n" +
                        "To understand the impact of social media on mental health, it is essential to first explore the benefits that these platforms provide. Social media can foster a sense of community, offer support networks, and facilitate the sharing of information and resources. For many individuals, especially those who may feel isolated or marginalized, these platforms serve as vital tools for connection and self-expression. Moreover, social media has enabled greater awareness and discussion about mental health issues, helping to reduce stigma and promote access to support.\n" +
                        "\n" +
                        "However, alongside these benefits, there are significant concerns regarding the potential negative effects of social media on mental health. The prevalence of cyberbullying, the pressure to present a curated and idealized version of oneself, and the constant exposure to others’ highlight reels can contribute to feelings of inadequacy, anxiety, and depression. Additionally, excessive use of social media can lead to addiction-like behaviors, impacting sleep patterns and overall well-being.\n" +
                        "\n" +
                        "This essay will delve into the dual-edged nature of social media’s impact on mental health. It will examine how social media can both support and undermine mental health, focusing on empirical research and real-world examples. By exploring the complexities of this relationship, the essay aims to provide a comprehensive understanding of how social media influences mental health and offer insights into how individuals and society can navigate these challenges effectively.")
            , Pair("Introduction","In the 21st century, technology has revolutionized every aspect of our lives, and education is no exception. The integration of digital tools and resources into the classroom has transformed traditional teaching methods, offering both opportunities and challenges for educators and students alike. From interactive learning platforms to virtual classrooms, technology has made education more accessible, personalized, and engaging than ever before. However, this rapid advancement also raises important questions about the effectiveness of these tools, the digital divide, and the potential consequences for student learning and teacher effectiveness. This essay will explore the multifaceted impact of technology on education, examining both the benefits and drawbacks, and providing insights into how educators can harness these innovations to enhance the learning experience while addressing the challenges that come with them.")
        )

    fun Activity.setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    fun getDashboardTabs(): List<Int> {
        return arrayListOf<Int>().apply {
            this.add(R.drawable.book_open)
            this.add(R.drawable.bookmark)
            this.add(R.drawable.tv)
            this.add(R.drawable.bell)
            this.add(R.drawable.user)
        }
    }
}