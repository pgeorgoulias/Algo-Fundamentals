from queue import PriorityQueue
import time
import networkx as nx
import matplotlib.pyplot as plt

def dijkstra_shortest_path(graph, start, goal):
    """
    Finds the shortest path between two users in a weighted social network with visualization.
    :param graph: Dictionary representing the adjacency list with weights.
    :param start: The starting user (source node).
    :param goal: The target user (destination node).
    :return: List representing the shortest weighted path from start to goal.
    """
    if start == goal:
        return [start]
    
    pq = PriorityQueue()
    pq.put((0, start, [start]))  # (cost, current node, path taken)
    visited = set()
    
    while not pq.empty():
        cost, current_node, path = pq.get()
        
        if current_node in visited:
            continue
        visited.add(current_node)
        
        print(f"Processing Node: {current_node} with cost {cost}")
        print(f"Current Path: {path}")
        time.sleep(1)  # Pause for visualization
        
        if current_node == goal:
            print(f"Found goal {goal}! Path: {path} with total cost {cost}")
            visualize_graph(graph, path)
            return path
        
        for neighbor, weight in graph.get(current_node, {}).items():
            if neighbor not in visited:
                pq.put((cost + weight, neighbor, path + [neighbor]))
                print(f"Queue Updated: {[item[1] for item in pq.queue]}")
                print(f"Visited Nodes: {visited}")
        print("---")
    
    return None  # No path found

def visualize_graph(graph, path):
    """Visualizes the social network graph and highlights the shortest path."""
    G = nx.Graph()
    for node, neighbors in graph.items():
        for neighbor, weight in neighbors.items():
            G.add_edge(node, neighbor, weight=weight)
    
    pos = nx.spring_layout(G)
    plt.figure(figsize=(8, 6))
    
    # Draw nodes and edges
    nx.draw(G, pos, with_labels=True, node_color='lightblue', edge_color='gray', node_size=2000, font_size=10)
    
    # Highlight the shortest path
    if path:
        path_edges = list(zip(path, path[1:]))
        nx.draw_networkx_edges(G, pos, edgelist=path_edges, edge_color='red', width=2)
    
    plt.title("SN Shortest Path")
    plt.show()

# Example Weighted Social Network Graph
social_network = {
    'Athens': {'London': 2, 'Rome': 1},
    'London': {'Rome': 2, 'New York': 3},
    'New York': {'Singapore': 5, 'Sydney': 7},
    'Sydney': {'London': 2, },
    'Singapore': {'New York': 2, 'Rome': 1},
    'Rome': {'Athens': 1},
    'Buenos Aires': {'Sydney': 3, 'London': 4},
    'Zanzibar': {'London': 6, 'Rome': 4},
    'Dubai': {'Athens': 4},
    'Beijin': { 'New York': 6},
    'Berlin': {'Dubai': 6, 'Beijin': 7}
}

# Find shortest connection path
start_user = 'Athens'
goal_user = 'Sydney'
shortest_path = dijkstra_shortest_path(social_network, start_user, goal_user)
print(f"Shortest path from {start_user} to {goal_user}: {shortest_path}")
