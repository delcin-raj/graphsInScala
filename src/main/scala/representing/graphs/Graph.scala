package representing.graphs

trait Graph[V] {
  def vertices: List[V]

  def edges: List[(V,V)]

  def addEdge(a: V,b: V): Graph[V]

  def neighbors(a: V): List[V]
}

object Graph {
  def apply[V](adjList: Map[V,List[V]]): DirectedGraph[V] =
    new DirectedGraph[V](adjList)

  def apply[V](): DirectedGraph[V] = new DirectedGraph(Map[V,List[V]]())
}
