package representing.graphs

import scala.collection.mutable

class DirectedGraph[V](val adjList: Map[V,List[V]]) extends Graph[V] {
  override val vertices: List[V] = {
    val set = mutable.HashSet[V]()
    adjList.foreach{case (u,vs) =>
      set.add(u)
      vs.foreach(v =>set.add(v))
    }
    set.toList
  }

  override val edges: List[(V, V)] = adjList.map{
    case (k,vs) => vs.map(v => (k,v))
  }.toList.flatten

  override def addEdge(a: V, b: V): DirectedGraph[V] = {
    new DirectedGraph(adjList + (a -> (b :: neighbors(a))))
  }

  override def neighbors(a: V): List[V] = adjList.getOrElse(a,Nil)
}
