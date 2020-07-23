# -*- coding: utf-8 -*-
"""
Created on Tue Mar 10 22:23:31 2020

@author: Lenovo
"""
import numpy as np
import matplotlib.pyplot as plt
import copy


#give the current state, action and reward map, output the next state
def Next_state_space(state,action,reward):
    next_state=[]
    flag=0 
    if action == 'up':
            if state[0]>0 and reward[state[0]-1,state[1]]!=9:
                temp=state[:]
                temp[0]-=1
                next_state.append(temp)
            else:
                flag=1
            if state[1]>0 and reward[state[0],state[1]-1]!=9:
                temp=state[:]
                temp[1]-=1
                next_state.append(temp)
            else:
                flag=1
            if state[1]<(n-1) and reward[state[0],state[1]+1]!=9:
                temp=state[:]
                temp[1]+=1
                next_state.append(temp)
            else:
                flag=1
            if flag==1:
                next_state.append(state)
                
    elif action == 'down':
            if state[0]<(m-1) and reward[state[0]+1,state[1]]!=9:
                temp=state[:]
                temp[0]+=1
                next_state.append(temp)
            else:
                flag=1
            if state[1]>0 and reward[state[0],state[1]-1]!=9:
                temp=state[:]
                temp[1]-=1
                next_state.append(temp)
            else:
                flag=1
            if state[1]<(n-1) and reward[state[0],state[1]+1]!=9:
                temp=state[:]
                temp[1]+=1
                next_state.append(temp)
            else:
                flag=1
            if flag==1:
                next_state.append(state)
                
    elif action == 'left':
            if state[1]>0 and reward[state[0],state[1]-1]!=9:
                temp=state[:]
                temp[1]-=1
                next_state.append(temp)
            else:
                flag=1
            if state[0]>0 and reward[state[0]-1,state[1]]!=9:    
                temp=state[:]
                temp[0]-=1
                next_state.append(temp)
            else:
                flag=1
            if state[0]<(m-1) and reward[state[0]+1,state[1]]!=9:
                temp=state[:]
                temp[0]+=1
                next_state.append(temp)
            else:
                flag=1
            if flag==1:
                next_state.append(state)
                
    elif action == 'right':
            if state[1]<(n-1) and reward[state[0],state[1]+1]!=9:
                temp=state[:]
                temp[1]+=1
                next_state.append(temp)
            else:
                flag=1
            if state[0]>0 and reward[state[0]-1,state[1]]!=9:    
                temp=state[:]
                temp[0]-=1
                next_state.append(temp)
            else:
                flag=1
            if state[0]<(m-1) and reward[state[0]+1,state[1]]!=9:
                temp=state[:]
                temp[0]+=1
                next_state.append(temp)
            else:
                flag=1
            if flag==1:
                next_state.append(state)
                
    return next_state        


#give the current state, action, reward map and next state, output the corresponding transition probability
def Trans_prob(state,action,next_state,reward):
    if action == 'up':
        if next_state[0]==state[0]-1 and next_state[1]==state[1]:
            trans_prob=0.8
        elif next_state[0]==state[0] and next_state[1]==state[1]+1:
            trans_prob=0.1
        elif next_state[0]==state[0] and next_state[1]==state[1]-1:
            trans_prob=0.1
        elif next_state[0]==state[0] and next_state[1]==state[1]:
            trans_prob = 0
            if ( state[0]>0 and reward[state[0]-1,state[1]]==9) or state[0]==0:
                trans_prob+=0.8
            if state[1]==0 or (state[1]>0 and reward[state[0],state[1]-1])==9:
                trans_prob+=0.1
            if state[1]==(n-1) or (state[1]<(n-1) and reward[state[0],state[1]+1])==9:
                trans_prob+=0.1

    elif action == 'down':
        if next_state[0]==state[0]+1 and next_state[1]==state[1]:
            trans_prob=0.8
        elif next_state[0]==state[0] and next_state[1]==state[1]+1:
            trans_prob=0.1
        elif next_state[0]==state[0] and next_state[1]==state[1]-1:
            trans_prob=0.1
        elif next_state[0]==state[0] and next_state[1]==state[1]:
            trans_prob = 0
            if (state[0]<(m-1) and reward[state[0]+1,state[1]]==9) or state[0]==(m-1):
                trans_prob+=0.8
            if state[1]==0 or (state[1]>0 and reward[state[0],state[1]-1])==9:
                trans_prob+=0.1
            if state[1]==(n-1) or (state[1]<(n-1) and reward[state[0],state[1]+1])==9:
                trans_prob+=0.1
        
        
    elif action == 'left':
        if next_state[0]==state[0]+1 and next_state[1]==state[1]:
            trans_prob=0.1
        elif next_state[0]==state[0] and next_state[1]==state[1]-1:
            trans_prob=0.8
        elif next_state[0]==state[0]-1 and next_state[1]==state[1]:
            trans_prob=0.1
        elif next_state[0]==state[0] and next_state[1]==state[1]:
            trans_prob = 0
            if (state[1]>0 and reward[state[0],state[1]-1]==9) or state[1]==0:
                trans_prob+=0.8
            if (state[0]<(m-1) and reward[state[0]+1,state[1]]==9) or state[0]==(m-1):
                trans_prob+=0.1
            if (state[0]>0 and reward[state[0]-1,state[1]]==9) or state[0]==0:
                trans_prob+=0.1
        
    elif action == 'right':
        if next_state[0]==state[0]+1 and next_state[1]==state[1]:
            trans_prob=0.1
        elif next_state[0]==state[0] and next_state[1]==state[1]+1:
            trans_prob=0.8
        elif next_state[0]==state[0]-1 and next_state[1]==state[1]:
            trans_prob=0.1
        elif next_state[0]==state[0] and next_state[1]==state[1]:
            trans_prob = 0
            if (state[1]<(n-1) and reward[state[0],state[1]+1]==9) or state[1]==(n-1):
                trans_prob+=0.8
            if (state[0]<(m-1) and reward[state[0]+1,state[1]]==9) or state[0]==(m-1):
                trans_prob+=0.1
            if (state[0]>0 and reward[state[0]-1,state[1]]==9) or state[0]==0:
                trans_prob+=0.1

    return(trans_prob)


#given the current state (represented by i, j), available actions, currently policy space, current expected utility of all states and reward map, output the optimal expected utility and the updated policy with the action that optimize the expected utility    
def Qoptimal(i,j,actionspace,policy_space,value,reward,T1,T2):
    Q=[]
    state=[i,j]
    if state==T1 or state==T2:  #terminal states need not to update expected utilities and they do not have corresponding actions
        Qopt=0
        policy_space[state[0],state[1]] = ['nil']
    else:    
        for action in actionspace:
            expected=0
            next_state_space=Next_state_space(state,action,reward)
            for next_state in next_state_space:
                trans_prob=Trans_prob(state,action,next_state,reward)
                expected+=trans_prob*value[next_state[0],next_state[1]]
            Q.append(expected)
        Qopt=max(Q)
        indices = [ee for ee, x in enumerate(Q) if x == Qopt]
        # print(indices)
        p=[]
        for ee in indices:
            p.append(actionspace[ee])
        policy_space[state[0],state[1]] = p
    return Qopt,policy_space



#construct reward map for the grid world
reward = np.matrix('-5 -0.1 1 3 9 -1 -2 -3 -1 -5; -0.1 -3 -0.1 -5 9 2 9 9 -0.1 -0.1; 1 9 -0.1 -0.1 -0.1 -0.1 9 -0.1 1 -0.1; -0.1 9 2 1 9 -2 9 1 -0.1 -0.1; -0.1 -0.1 3 -20 3 -2 20 -2 3 -2; -0.1 9 -0.1 2 2 -1 -2 9 -0.1 9; -0.1 3 1 -0.1 -0.1 -3 -0.1 1 -0.1 -1; -0.1 -2 -0.1 -2 9 -0.1 -2 -0.1 9 -0.1; -0.1 +3 9 -0.1 3 -0.1 -0.1 3 -2 -0.1; -5 -0.1 3 -0.1 -0.1 4 -0.1 -2 -0.1 -5') 
print("Via string input : \n", reward, "\n\n") 



T1=[4,3]
T2=[4,6]
m=reward.shape[0]
n=reward.shape[1]
value=np.zeros((m,n))  #initialize expected utilities of all states to be 0
discount=0.9  #set discount factor
actionspace=['up','down','left','right'] #define the available actions
counter=0
state=[3,2] #optional initial state


#assume initial policy_space is go up at every state
policy_space={(0, 0): ['up'], (0, 1): ['up'], (0, 2): ['up'], (0, 3): ['up'], 
        (0, 5): ['up'], (0, 6): ['up'], (0, 7): ['up'], (0, 8): ['up'], (0, 9): ['up'], (1, 0): ['up'], (1, 1): ['up'], (1, 2): ['up'], (1, 3): ['up'], 
        (1, 5): ['up'], (1, 8): ['up'], (1, 9): ['up'], (2, 0): ['up'], (2, 2): ['up'], 
        (2, 3): ['up'], (2, 4): ['up'], (2, 5): ['up'], (2, 7): ['up'], (2, 8): ['up'], (2, 9): ['up'],
        (3, 0): ['up'], (3, 2): ['up'], (3, 3): ['up'], (3, 5): ['up'], (3, 7): ['up'], (3, 8): ['up'], (3, 9): ['up'],
        (4, 0): ['up'], (4, 1): ['up'], (4, 2): ['up'], (4, 3): ['nil'], (4, 4): ['up'], (4, 5): ['up'], (4, 6): ['nil'], (4, 7): ['up'], (4, 8): ['up'], (4, 9): ['up'], 
        (5, 0): ['up'], (5, 2): ['up'], (5, 3): ['up'], (5, 4): ['up'], (5, 5): ['up'], (5, 6): ['up'], (5, 8): ['up'],
        (6, 0): ['up'], (6, 1): ['up'], (6, 2): ['up'], (6, 3): ['up'], (6, 4): ['up'], (6, 5): ['up'], (6, 6): ['up'], (6, 7): ['up'], (6, 8): ['up'], (6, 9): ['up'],
        (7, 0): ['up'], (7, 1): ['up'], (7, 2): ['up'], (7, 3): ['up'], (7, 5): ['up'], (7, 6): ['up'], (7, 7): ['up'], (7, 9): ['up'],
        (8, 0): ['up'], (8, 1): ['up'], (8, 3): ['up'], (8, 4): ['up'], (8, 5): ['up'], (8, 6): ['up'], (8, 7): ['up'], (8, 8): ['up'], (8, 9): ['up'],
        (9, 0): ['up'], (9, 1): ['up'], (9, 2): ['up'], (9, 3): ['up'], (9, 4): ['up'], (9, 5): ['up'], (9, 6): ['up'], (9, 7): ['up'], (9, 8): ['up'], (9, 9): ['up']} 


#map the 2-dimensional keys of policy_space  to 1-dimensional keys
position_pointer={(0, 0): 0, (0, 1): 1, (0, 2): 2, (0, 3): 3, 
        (0, 5): 4, (0, 6): 5, (0, 7): 6, (0, 8): 7, (0, 9): 8, (1, 0): 9, (1, 1): 10, (1, 2): 11, (1, 3): 12, 
        (1, 5): 13, (1, 8): 14, (1, 9): 15, (2, 0): 16, (2, 2): 17, 
        (2, 3): 18, (2, 4): 19, (2, 5): 20, (2, 7): 21, (2, 8): 22, (2, 9): 23,
        (3, 0): 24, (3, 2): 25, (3, 3): 26, (3, 5): 27, (3, 7): 28, (3, 8): 29, (3, 9): 30,
        (4, 0): 31, (4, 1): 32, (4, 2): 33, (4, 3): 34, (4, 4): 35, (4, 5): 36, (4, 6): 37, (4, 7): 38, (4, 8): 39, (4, 9): 40, 
        (5, 0): 41, (5, 2): 42, (5, 3): 43, (5, 4): 44, (5, 5): 45, (5, 6): 46, (5, 8): 47,
        (6, 0): 48, (6, 1): 49, (6, 2): 50, (6, 3): 51, (6, 4): 52, (6, 5): 53, (6, 6): 54, (6, 7): 55, (6, 8): 56, (6, 9): 57,
        (7, 0): 58, (7, 1): 59, (7, 2): 60, (7, 3): 61, (7, 5): 62, (7, 6): 63, (7, 7): 64, (7, 9): 65,
        (8, 0): 66, (8, 1): 67, (8, 3): 68, (8, 4): 69, (8, 5): 70, (8, 6): 71, (8, 7): 72, (8, 8): 73, (8, 9): 74,
        (9, 0): 75, (9, 1): 76, (9, 2): 77, (9, 3): 78, (9, 4): 79, (9, 5): 80, (9, 6): 81, (9, 7): 82, (9, 8): 83, (9, 9): 84  } 


print("0th iteration policy_space: \n", policy_space, "\n\n")


estimates=[] #initiakize the list which will contain the utility estimates
iter_flag=1
iter_no=1

while iter_flag==1:   #repeat policy iteration until the policy is no longer updated
    
    
    #construct the linear system of equations that corresponds to the current policy and solve it to obtain the expected utilities for each state
    old_policy_space = copy.deepcopy(policy_space)
    MatrixMap=[]
    mapping={}
    constant=[]
    for i in range(m):
        for j in range(n):
            if reward[i,j]!=9: #wall states are not considered
                constant.append(reward[i,j])
                mapping={}  #teminal states will have empty value for their keys in policy_space dictionary
                if reward[i,j]!=20 and reward[i,j]!=-20:
                    next_state_space=Next_state_space([i,j],policy_space[i,j][0],reward)
                    for next_state in next_state_space:
                        trans_prob=Trans_prob([i,j],policy_space[i,j][0],next_state,reward)
                        mapping[position_pointer[next_state[0],next_state[1]]]=trans_prob
                MatrixMap.append(mapping)
                
    constant_vector=-1*np.asarray(constant)
    constant_vector = constant_vector[:,None]
    Matrix=np.zeros((len(MatrixMap),len(MatrixMap)))
    row_index=0
    for row in MatrixMap:
        for key in row:
            Matrix[row_index,key]=row[key]
        row_index+=1
    Matrix=Matrix*discount
    for i in range(len(MatrixMap)):
        Matrix[i,i]-=1
    U=np.linalg.inv(Matrix).dot(constant_vector)  #use matrix inverse of sovle the linear system
    
    k=0
    for i in range(m):   #update expected utilities of all states
        for j in range(n):
            if reward[i,j]!=9:
                value[i,j]=U[k,0]
                k+=1
    
    estimates.append(value[4,9])   #update utility estimates
    
    for i in range(m):
        for j in range(n):
            if reward[i,j]!=9:
                Qopt,policy_space=Qoptimal(i,j,actionspace,policy_space,value,reward,T1,T2)  #update the expected utility map and optimal actions accoridng to policy iteration rule
    
    print(iter_no,"th iteration policy_space: \n", policy_space, "\n\n")
    if old_policy_space==policy_space:  #determine if the stopping criterion is satisfied
        iter_flag=0
    else:
        iter_no+=1


# Plot of utility estimates as a function of the number of iterations 
iter=list(range(len(estimates)))
[x+1 for x in iter]
plt.figure()
plt.plot(iter,estimates)
plt.show()
print(estimates)




# Utilities of all states 
fig, ax = plt.subplots(m, n)
for i in range(m):
    for j in range(n):
        ax[i, j].set_xticks([])
        ax[i, j].set_yticks([])
        x_pos = []
        y_pos = []
        x_direct=[]
        y_direct =[]
        if reward[i,j]!=9:
            ax[i,j].text(0.5, 0.4, str(round(value[i, j],2)),
                      fontsize=14, ha='center')
fig.subplots_adjust(wspace=0.1)
fig.subplots_adjust(hspace=0.1)
fig


# Plot of optimal policy_space 
fig, ax = plt.subplots(m, n)
for i in range(m):
    for j in range(n):
        ax[i, j].set_xticks([])
        ax[i, j].set_yticks([])
        x_pos = []
        y_pos = []
        x_direct=[]
        y_direct =[]
        if reward[i,j]!=9:
            for k in policy_space[i,j]:
                if k=='up':
                    x_direct.append(0)
                    y_direct.append(1)
                    x_pos.append(0)
                    y_pos.append(0)
                if k=='down':
                    x_direct.append(0)
                    y_direct.append(-1)
                    x_pos.append(0)
                    y_pos.append(0)
                if k=='left':
                    x_direct.append(-1)
                    y_direct.append(0)
                    x_pos.append(0)
                    y_pos.append(0)
                if k=='right':
                    x_direct.append(1)
                    y_direct.append(0)
                    x_pos.append(0)
                    y_pos.append(0)
            ax[i,j].quiver(x_pos,y_pos,x_direct,y_direct,scale=3,width=0.05)
            title=''
            for k in range(len(policy_space[i,j])):
                if k==0:
                    title=title+policy_space[i,j][k]
                else:
                    title=title+','+policy_space[i,j][k]
            plt.text(0.5, 1.05, title,
         horizontalalignment='center',
         fontsize=6,
         transform = ax[i,j].transAxes)


fig.subplots_adjust(wspace=0.1)
fig.subplots_adjust(hspace=0.3)
fig