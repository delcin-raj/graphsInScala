package representing.graphs

class UndirectedGraph[V](adjList: Map[V,List[V]]) extends DirectedGraph[V](adjList) {
  override def addEdge(a: V, b: V): UndirectedGraph[V] = {
    //super.addEdge(a, b)
    //super.addEdge(b,a)
    val addA = adjList.updated(a,b :: adjList.getOrElse(a,Nil))
    val addAB = addA.updated(b,a :: addA.getOrElse(b,Nil))
    new UndirectedGraph[V](addAB)
  }
}
