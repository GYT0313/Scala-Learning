
var assertionsEnabled = true
def myAssert(predicate: () => Boolean) = 
if (assertionsEnabled && !predicate())
  throw new Exception("assert exception")

def byNameAssert(predicate: => Boolean) = 
  if (assertionsEnabled && !predicate)
    throw new Exception("assert exception")

  