package com.gyt

object Extractor14_2 {
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

    object Domain {
      // 注入
      def apply(parts: String*): String = parts.reverse.mkString(".")
      // 提取
      def unapplySeq(whole: String): Option[Seq[String]] = Some(whole.split("\\.").reverse)
    }

    // 查找名为tom，后缀为.com 的邮件地址
    def isTomInDotCom(s: String): Boolean = s match {
      case EMail("tom", Domain("com", _*)) => true
      case _ => false
    }

    // test
    println(isTomInDotCom("tom@sun.com"))
    println(isTomInDotCom("TOM@sun.com"))
    println(isTomInDotCom("tom@sun.cn"))

    println("-" * 10)

    object ExpandedEMail {
      def unapplySeq(email: String): Option[(String, Seq[String])] = {
        val parts = email split "@"
        if (parts.length == 2)
          Some(parts(0), parts(1).split("\\.").reverse)
        else
          None
      }
    }

    val s = "tom@support.epfl.ch"
    val ExpandedEMail(name, topDom, subdoms @ _*) = s
    println(name)
    println(topDom)
    println(subdoms)
  }
}
