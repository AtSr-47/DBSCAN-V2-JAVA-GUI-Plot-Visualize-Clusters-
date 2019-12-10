# DBSCAN-V2-JAVA-GUI-Plot-Visualize-Clusters-
DBSCAN V2+ JAVA GUI (Plot/Visualize Clusters)
PSEUDOCODE
-------------------------------------

DBSCAN(D, eps, MinPts)
   C = 0
   for each unvisited point P in dataset D
      mark P as visited
      N = getNeighbors (P, eps)
      if sizeof(N) < MinPts
         mark P as NOISE
      else
         C = next cluster
         expandCluster(P, N, C, eps, MinPts)
          
expandCluster(P, N, C, eps, MinPts)
   add P to cluster C
   for each point P' in N 
      if P' is not visited
         mark P' as visited
         N' = getNeighbors(P', eps)
         if sizeof(N') >= MinPts
            N = N joined with N'
      if P' is not yet member of any cluster
         add P' to cluster C
		 
---------------------------------------
![Test Image 1]()







