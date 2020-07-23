

# -*- coding: utf-8 -*-
"""
Created on Mon Mar  9 21:31:14 2020

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
def Qoptimal(i,j,actionspace,policy_space,value,reward):
    Q=[]
    state=[i,j]
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
reward = np.matrix('1 9 1 -0.04 -0.04 1; -0.04 -1 -0.04 1 9 -1; -0.04 -0.04 -1 -0.04 1 -0.04; -0.04 -0.04 -0.04 -1 -0.04 1; -0.04 9 9 9 -1 -0.04; -0.04 -0.04 -0.04 -0.04 -0.04 -0.04') 
print("Via string input : \n", reward, "\n\n") 



m=reward.shape[0]
n=reward.shape[1]
value=np.zeros((m,n)) #initialize expected utilities of all states to be 0
discount=0.99 #set discount factor
actionspace=['up','down','left','right'] #define the available actions
counter=0
state=[3,2] #initial state
policy_space={} #initiakize the policy to be empty 
estimates=[] #initiakize the list which will contain the utility estimates

iter_flag=1
iter_no=1
c=0.01
epsilon = c*1
while iter_flag==1: #repeat value iteration until the error threshold is satisfied
    delta=np.zeros((m,n))
    old_value = copy.deepcopy(value)
    for i in range(m):
        for j in range(n):
            if reward[i,j]!=9: #wall states are not considered
                Qopt,policy_space=Qoptimal(i,j,actionspace,policy_space,value,reward) #get updated optimal expected utilities and corresponding action(s)
                value[i,j]=reward[i,j]+discount*Qopt     #update the expected utility map accoridng to value iteration rule
                if abs(value[i,j]-old_value[i,j])>delta[i,j]:
                    delta[i,j]=abs(value[i,j]-old_value[i,j]) #update the error of coniditon is satisfied
    print(iter_no,"th iteration value: \n", value, "\n\n") 
    print(iter_no,"th iteration policy_space: \n", policy_space, "\n\n")
    iter_flag=0
    for i in range(m):   #determine if the stopping criterion is satisfied
        for j in range(n):
            if delta[i,j]>=(epsilon*(1-discount)/discount):
                iter_flag=1         
    else:
        iter_no+=1
    estimates.append(value[5,5]) #update utility estimates
    
    
# Plot of utility estimates as a function of the number of iterations 
iter=list(range(len(estimates)))
[x+1 for x in iter]
plt.figure()
plt.plot(iter,estimates)
plt.show()


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
            # ax[i,j].set_title(title)
            plt.text(0.5, 1.02, title,
         horizontalalignment='center',
         fontsize=10,
         transform = ax[i,j].transAxes)


fig.subplots_adjust(wspace=0.1)
fig.subplots_adjust(hspace=0.3)
fig




        


