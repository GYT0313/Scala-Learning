package com.gyt

object Extractor14_1 {
  def main(args: Array[String]): Unit = {
    object EMail {
      // 注入（可选）
      def apply(user: String, domain: String) = user + "@" + domain
      // 提取（必须）
      def unapply(str: String): Option[(String, String)] = {
        val parts = str split "@"
        if (parts.length == 2) Some(parts(0), parts(1)) else None
      }
    }

    object Twice {
      def apply(s: String): String = s + s
      // 如果是连续重复的则返回一半，如DIDI 返回 DI
      def unapply(s: String): Option[String] = {
        val length = s.length / 2
        val half = s.substring(0, length)
        if (half == s.substring(length)) Some(half) else None
      }
    }

    object UpperCase {
      // s.toUpperCase == s 则返回true
      def unapply(s: String): Boolean = s.toUpperCase == s
    }

    def userTwiceUpper(s: String) = s match {
      case EMail(Twice(user @ UpperCase()), domain) => "match: " + user + " in domain " + domain
      case _ => "no match"
    }

    println(userTwiceUpper("DIDI@qq.com"))
    println(userTwiceUpper("DIdi@qq.com"))
    println(userTwiceUpper("DIDY@qq.com"))
  }
}
