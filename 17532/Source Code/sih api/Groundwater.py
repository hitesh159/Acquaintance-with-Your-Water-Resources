import pandas as pd
import numpy as np
import statistics as stat
import matplotlib.pyplot as plt

dataset = pd.read_csv("gujrat_groundwater.csv")
#dataset = dataset.drop("WLCODE", axis =1)
dataset = dataset.iloc[:35,:]
dataset = dataset.fillna(method='ffill')

dataset["LEVEL"]= dataset["LEVEL"].apply(float)
Tube_Well =0
Dug_Well = 0
for i in range(35):
    if(dataset.at[i,"SITE_TYPE"]=='Tube Well'):
        Tube_Well = Tube_Well + 1
        #print("Tube_Well  "+ dataset.at[i,"SITE_NAME"])
    elif(dataset.at[i,"SITE_TYPE"]=='Dug Well'):
        Dug_Well = Dug_Well + 1
        #print("Dug_Well  " +dataset.at[i,"SITE_NAME"])
        
    if(dataset.at[i,"LEVEL"]>0 and dataset.at[i,"LEVEL"]<=2):
        dataset.at[i,"Category"]=3
    elif(dataset.at[i,"LEVEL"]>2 and dataset.at[i,"LEVEL"]<=5):
        dataset.at[i,"Category"]=2     
    elif(dataset.at[i,"LEVEL"]>5):
        dataset.at[i,"Category"]=1        
        
dataset.to_csv("groundwater_check.csv")
