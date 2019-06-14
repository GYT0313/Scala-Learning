// Option
val captials = Map("China"->"Beijing")

captials get "China"
captials get "England"


def show(x: Option[String]) = x match {
  case Some(s) => s
  case None => "?"
}
show(captials get "China")
show(captials get "England")