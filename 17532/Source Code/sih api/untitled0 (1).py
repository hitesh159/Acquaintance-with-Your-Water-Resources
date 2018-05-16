#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Mar 28 04:16:09 2018

@author: abstergo
"""

from sklearn.externals import joblib
import tensorflow as tf
import numpy as np
#import pickle
import _pickle as pickle
import pandas as pd
from sklearn.preprocessing import MinMaxScaler
import matplotlib.pyplot as plt

test = pd.read_csv("delete.csv",header = None)
sess=tf.Session()    
#First let's load meta graph and restore weights
saver = tf.train.import_meta_graph('/tmp/model.ckpt.meta')
saver.restore(sess,tf.train.latest_checkpoint('/tmp/'))
graph = tf.get_default_graph()
pred1 = sess.run(graph.get_tensor_by_name("out_1:0"), feed_dict={X: X_test})