// append 轮廓
def append[T](xs: List[T], ys: List[T]): List[T] = 
  xs match {
    case List() => ???
    case x :: xs1 => ???
  }



def append[T](xs: List[T], ys: List[T]): List[T] = 
  xs match {
    case List() => ys
    case x :: xs1 => x :: append(xs1, ys)
  }
