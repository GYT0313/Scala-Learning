package gyt {
  package navigation {
    class Navigator {
      // 简短
      val map = new StarMap
    }
    class StarMap
  }
  class Ship {
    // 加上包名
    val nav = new navigation.Navigator
  }
  package fleets {
    class Fleet {
      // 属于同一级包
      def addShip() = {
        new Ship
      }
    }
  }
}