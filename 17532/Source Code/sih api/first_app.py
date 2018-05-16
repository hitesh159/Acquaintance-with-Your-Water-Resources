# -*- coding: utf-8 -*-
"""
Created on Thu Mar 29 21:29:04 2018

@author: hitesh
"""

from flask import Flask,request,jsonify
import pandas as pd
import os
import json

ground=74
app=Flask(__name__)
my_dir = os.path.dirname(__file__)
file_path = os.path.join(my_dir, 'Final.csv')
dataframe=pd.read_csv(file_path)

file_path = os.path.join(my_dir, 'crop_req.csv')
dataframe_crop=pd.read_csv(file_path)

file_path = os.path.join(my_dir, 'groundwater_check.csv')
dataframe_ground=pd.read_csv(file_path)

file_path = os.path.join(my_dir, 'groundwater_check.csv')
ground_last=pd.read_csv(file_path)

def distance(x1, y1, x2, y2):
    dx = x2 - x1
    dy = y2 - y1
    dsquared = dx**2 + dy**2
    result = dsquared**0.5
    return result

@app.route('/get_crops/')
def get_crops():
    crops=list(set(dataframe_crop.iloc[:,0].values))
    return jsonify({"crops":crops})

for i in range(408):
    if(dataframe.at[i,"2015"]<0):
        dataframe.at[i,"2015"]= dataframe.at[i,"2015"]*-1

@app.route('/get_all/')
def get_all():
    states=list(set(dataframe.iloc[:,0].values))
    return jsonify({"states":states})

@app.route('/get_months/')
def get_months():
    months=list(set(dataframe.iloc[:,1].values))
    return jsonify({"months":months})

@app.route('/')
def index():
    return "index"

@app.route('/tehsil_list')
def tehsil():
    tehsils=list(set(dataframe_ground.iloc[:,3].values))
    return jsonify({"tehsils":tehsils})

@app.route('/gw_level/',methods=['GET'])
def gw_level():
    data=request.args
    tehsil=data['tehsil']
    for i in range(ground):
        if(tehsil==dataframe_ground.at[i,'TEH_NAME']):
            return jsonify({'level':dataframe_ground.at[i,'LEVEL'],'category':dataframe_ground.at[i,'Category']})
    else:
        return(jsonify({'error':'bad request'}))

@app.route('/groundwater/')
def groundwater():
    list=[]
    for i in range(ground):
        list.append({'LAT':dataframe_ground.at[i,'LAT'],'LON':dataframe_ground.at[i,'LON'],'Category':dataframe_ground.at[i,'Category']})
    return json.dumps(list)


@app.route('/ground_last/')
def get_list():
    states=list(set(ground_last.iloc[:,0].values))
    return jsonify({'states':states})

@app.route('/grd_lat_lng/')
def grd_lat_lng():
    list=[]
    for i in range(ground):
        list.append({'LAT':ground_last.at[i,'LAT'],'LON':ground_last.at[i,'LON'],'Category':ground_last.at[i,'Category'],'Well_type':ground_last.at[i,'SITE_TYPE']})
    return json.dumps(list)


@app.route('/rainfall_predict/', methods=['GET'])
def predictor():
    data=request.args
    state_name=data['name']
    month=data['month']
    for i in range(408):
        if(dataframe.at[i,"SUBDIVISION"]==state_name and dataframe.at[i,"YEAR"]==month):
            return jsonify({"ans":dataframe.at[i,"2015"]})
    return jsonify({"ans":"error","name":state_name,"month":month})

@app.route('/graph/',methods=['GET'])
def graph():
    data=request.args
    state_name=data['name']
    month=data['month']
    for i in range(408):
        if(dataframe.at[i,"SUBDIVISION"]==state_name and dataframe.at[i,"YEAR"]==month):
            list=[]
            for j in range(15):
                list.append(dataframe.at[i,str(2015-j)])
            return jsonify({"ans":list})
    return jsonify({"ans":"error","name":state_name,"month":month})

@app.route('/nearest/',methods=['GET'])
def nearest():
    for i in range(408):
        lat=25.2677251
        lon=82.9890695
        min=10000000
        ans=0
        for i in range(74):
            lati=dataframe_ground.at[i,'LAT']
            longi=dataframe_ground.at[i,'LON']
            dis=distance(lat,lon,lati,longi)
            if dis<=min:
                min=dis
                ans=i
        return jsonify({'closest':{'lat':dataframe_ground.at[ans,'LAT'],'lon':dataframe_ground.at[ans,'LON']}})

@app.route('/crop_predictor/',methods=['GET'])
def crop_predictor():
    data=request.args
    state_name=data['name']
    month=data['month']

