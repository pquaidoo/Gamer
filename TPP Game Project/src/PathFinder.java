import java.util.ArrayList;

public class PathFinder {

    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    public PathFinder(GamePanel gp) {
        this.gp = gp;
        instantiateNode();
    }

    public void instantiateNode() {
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            node[col][row] = new Node(col, row);
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;

            }

        }
    }

    public void resetNodes() {
        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }

        }
        //RESET OTHER SETTINGS
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {

        resetNodes();

        //SET START GOAL NODE
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            //SET SOLID NODE

            //CHECK TILES
            int tileNum = gp.tileM.mapTileNum[col][row];//different from video because we dont have multiple maps.

            if (gp.tileM.Tiles[tileNum].collision == true) {
               // System.out.println("ive fallen and I cant get up");
                node[col][row].solid = true;

            }
            //SET COST
            getCost(node[col][row]);
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }

        }
    }

            public void getCost(Node node){
                //G cost
                int xDistance = Math.abs(node.col - startNode.col);
                int yDistance = Math.abs(node.row  - startNode.row);
                node.gCost = xDistance + yDistance;
                // H cost
                xDistance = Math.abs(node.col - goalNode.col);
                yDistance = Math.abs(node.row - goalNode.row);
                node.hCost = xDistance + yDistance;

                //F cost
                node.fCost= node.gCost + node.hCost;
            }
            public boolean search(){
                while(goalReached == false && step < 500) {

                    int col = currentNode.col;
                    int row = currentNode.row;

                    //Check the current Node
                    currentNode.checked = true;
                    openList.remove(currentNode);

                    //Open the up node
                    if (row - 1 >= 0) {
                        openNode(node[col][row - 1]);
                    }
                    //open left node
                    if (col - 1 >= 0) {
                        openNode(node[col - 1][row]);
                    }
                    //Open down node
                    if (row + 1 < gp.maxWorldRow) {
                        openNode(node[col][row + 1]);
                    }
                    //Open right node
                    if (col + 1 < gp.maxWorldRow) {
                        openNode(node[col + 1][row]);
                    }

                //find best nodes
                int bestNodeIndex=0;
                int bestNodefCost=999;

                for(int i = 0; i < openList.size();i++) {
                    //check if node's f cost is better
                    if (openList.get(i).fCost < bestNodefCost) {
                        bestNodeIndex = i;
                        bestNodefCost = openList.get(i).fCost;

                    } //if f cost is equal check g cost
                    else if (openList.get(i).fCost == bestNodefCost) {
                        if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                            bestNodeIndex = i;
                        }
                    }
                }


                    //if there is no node in the open list, end the loop
                    if(openList.size() == 0){
                        break;
                }
                    //after the koop openlist[bestNdoeIndex] is the next step( = currentNode)
                    currentNode = openList.get(bestNodeIndex);

                    if(currentNode == goalNode){
                        goalReached = true;
                        trackthePath();
                    }
                    step++;
                }
                return goalReached;
            }
            public void openNode(Node node){
                if(node.open == false && node.checked == false && node.solid == false){
                    node.open = true;
                    node.parent = currentNode;
                    openList.add(node);
                }
            }
        public void trackthePath(){

              Node current = goalNode;

              while(current != startNode){
                  pathList.add(0,current);
                  current = current.parent;
              }
            }

        }



