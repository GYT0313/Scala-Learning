// 统计不同单词
val text = "Hello world. Hello scala!"
val wordsArr = text.split("[ !,.]+")

// 创建空集
val words = mutable.Set.empty[String]
for (word <- wordsArr) {
  words += word.toLowerCase
}



// 统计单词出现的次数
def countWords(text: String) = {
  val counts = mutable.Map.empty[String, Int]
  for (rawWord <- text.split("[ ,!.]+")) {
    val oldCount = {
      if (counts.contains(rawWord)) counts(rawWord)
      else 0
    }
    // 计数
    counts += (rawWord -> (oldCount + 1))
  }
  counts
}


// 寻找字符串第一个最长字母及下标
def longest(words: Array[String]) = {
  var word = words(0)
  var idx = 0
  for (i <- 1 until words.length) {
    if (words(i).length > word.length) {
      word = words(i)
      idx = i
    }
  }
  (word, idx)
}
longest("Life is short, I use Python.".split("[ ,.]"))