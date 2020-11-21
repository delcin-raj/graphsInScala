package representing.graphs

import scala.collection.mutable

case class WeightedEdge[V](val target: V,weight: BigDecimal)
class WeightedGraph[V](adjList: Map[V,List[WeightedEdge[V]]]) extends Graph[V] {
  override def vertices: List[V] = {
    val set = mutable.HashSet[V]()
    adjList.foreach{case (u,vs) =>
      set.add(u)
      vs.foreach(v =>set.add(v.target))
    }
    set.toList
  }

  override def edges: List[(V, V)] =
    adjList.flatMap{
      case (k,wes) => wes.map(we => (k,we.target))
    }.toList

  def addEdge(a: V,weightedEdge: WeightedEdge[V]): WeightedGraph[V] =
    new WeightedGraph[V](adjList.updated(a,weightedEdge :: adjList.getOrElse(a,Nil)))

  override def addEdge(a: V, b: V): WeightedGraph[V] =
    addEdge(a,WeightedEdge(b,0))

  override def neighbors(a: V): List[V] =
    adjList.getOrElse(a,Nil).map(we => we.target)

  def neighboursWithWeights(a: V): List[WeightedEdge[V]] =
    adjList.getOrElse(a,Nil)

}
