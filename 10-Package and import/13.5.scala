package gyt {
  class Ship
}
package gyt2.fleets {
  class Fleet {
    def addShip() = {
      // 无法通过编译
      new Ship
    }
  }
}



package gyt {
  class Ship
}
package gyt2.fleets {
  class Fleet {
    def addShip() = {
      // 能够通过编译
      new gyt.Ship
    }
  }
}

def addShip() = {
  new _root_.gyt3.Ship
}