import copy

def misplaced_tiles(state, goal_state):
    misplaced_count = [] 
    for a, b in zip(state, goal_state):
        for x, y in zip(a, b):
            if x != y:
                misplaced_count.append(1)
    return sum(misplaced_count) 

def get_next_states(state):
    moves = [(0, 1), (0, -1), (1, 0), (-1, 0)]
    zero_row, zero_col = next((i, j) for i, row in enumerate(state) for j, val in enumerate(row) if val == 0)
    next_states = []
    for move in moves:
        new_row, new_col = zero_row + move[0], zero_col + move[1]
        if 0 <= new_row < 3 and 0 <= new_col < 3:
            new_state = copy.deepcopy(state)
            new_state[zero_row][zero_col], new_state[new_row][new_col] = new_state[new_row][new_col], new_state[zero_row][zero_col]
            next_states.append(new_state)
    return next_states

a=0
def calculate_f(state, goal_state):
    return a + misplaced_tiles(state, goal_state)

# initial_state = [
#     [2,8,3],
#     [1,6,4],
#     [7,0,5]
# ]
# goal_state = [
#     [1,2,3],
#     [8,0,4],
#     [7,6,5]
# ]

initial_state = []
print("Enter initial state:")
for _ in range(3):
    row = list(map(int, input().split()))
    initial_state.append(row)

# Take input of goal state from the user
goal_state = []
print("Enter goal state:")
for _ in range(3):
    row = list(map(int, input().split()))
    goal_state.append(row)

print("Initial State:")
[print(row) for row in initial_state]
initial_f = calculate_f(initial_state, goal_state)
print("f(x) =", initial_f)
a=a+1

next_states = get_next_states(initial_state)

def calculate_f_for_key(state):
    return calculate_f(state, goal_state)

best_state = min(next_states, key=calculate_f_for_key)

best_f = calculate_f(best_state, goal_state)

print("\nBest Next State:")
[print(row) for row in best_state]
print("f(x) of Best Next State:", best_f)

if best_f < initial_f:
    print()
else:
    print("No better state found.")