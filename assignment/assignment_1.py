# -*- coding: utf-8 -*-
"""
Created on Mon Mar  9 21:31:14 2020

@author: Lenovo
"""


import numpy as np
import random


def Next_state(state,action):
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




def next_state2(state,action):
    interval=random.uniform(0, 1)
    next_state=state[:]
    if action == 'up':
        if interval<0.8:
            if state[0]>0 and reward[state[0]-1,state[1]]!=9:
                next_state[0]-=1
        elif interval>=0.8 and interval<0.9:
            if state[1]>0 and reward[state[0],state[1]-1]!=9:
                next_state[1]-=1
        elif interval>=0.9:
            if state[1]<(n-1) and reward[state[0],state[1]+1]!=9:
                next_state[1]+=1
    elif action == 'down':
        if interval<0.8:
            if state[0]<(m-1) and reward[state[0]+1,state[1]]!=9:
                next_state[0]+=1
        elif interval>=0.8 and interval<0.9:
            if state[1]>0 and reward[state[0],state[1]-1]!=9:
                next_state[1]-=1
        elif interval>=0.9:
            if state[1]<(n-1) and reward[state[0],state[1]+1]!=9:
                next_state[1]+=1
    elif action == 'left':
        if interval<0.8:
            if state[1]>0 and reward[state[0],state[1]-1]!=9:
                next_state[1]-=1
        elif interval>=0.8 and interval<0.9:
            if state[0]>0 and reward[state[0]-1,state[1]]!=9:            
                next_state[0]-=1
        elif interval>=0.9:
            if state[0]<(m-1) and reward[state[0]+1,state[1]]!=9:
                next_state[0]+=1
    elif action == 'right':
        if interval<0.8:
            if state[1]<(n-1) and reward[state[0],state[1]+1]!=9:
                next_state[1]+=1
        elif interval>=0.8 and interval<0.9:
            if state[0]>0 and reward[state[0]-1,state[1]]!=9:            
                next_state[0]-=1
        elif interval>=0.9:
            if state[0]<(m-1) and reward[state[0]+1,state[1]]!=9:
                next_state[0]+=1
    return(next_state)    


def Trans_prob(state,action,next_state):
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

    
    
def Utility(state,actionspace,counter,value,m,n,discount,policy):
    if counter>1:
        return 0,policy,value
    else:
        counter+=1
        Qopt,policy,value=Qoptimal(state,actionspace,counter,value,m,n,discount,policy)
        U=reward[state[0],state[1]]+discount*Qopt 
        value[state[0],state[1]]=U
        return U,policy,value
    
def Qoptimal(state,actionspace,counter,value,m,n,discount,policy):
    Q=[]
    expected=0
    for action in actionspace:
        next_state_space=Next_state(state,action)
        for next_state in next_state_space:
            trans_prob=Trans_prob(state,action,next_state)
            U,policy,value=Utility(next_state,actionspace,counter,value,m,n,discount,policy)
            expected+=trans_prob*U
        Q.append(expected)
    Qopt=max(Q)
    policy[state[0],state[1]] = actionspace[Q.index(Qopt)]
    return Qopt,policy,value
    

    
reward = np.matrix('1 9 1 -0.04 -0.04 1; -0.04 -1 -0.04 1 9 -1; -0.04 -0.04 -1 -0.04 1 -0.04; -0.04 -0.04 -0.04 -1 -0.04 1; -0.04 9 9 9 -1 -0.04; -0.04 -0.04 -0.04 -0.04 -0.04 -0.04') 
print("Via string input : \n", reward, "\n\n") 



m=reward.shape[0]
n=reward.shape[1]
value=np.zeros((m,n))
discount=0.99
actionspace=['up','down','left','right']
counter=0
state=[3,2]
policy={}

U,policy,value=Utility(state,actionspace,counter,value,m,n,discount,policy)


# for i in range(50):
#     U(state)










