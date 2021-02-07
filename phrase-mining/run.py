import sys
import os
import json
import requests
import gzip
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from collections import Counter
from tqdm import tqdm
import numpy as np
sys.path.insert(0, 'src')
from auto import autophrase
from visual import visual


def main(targets):
    '''
    Runs the main project pipeline logic, given the targets.
    targets must contain: 'data', 'analysis', 'model'. 
    `main` runs the targets in order of data=>analysis=>model.
    '''
    with open('config/auto-params.json') as fh:
        auto_cfg = json.load(fh)
    autophrase(**auto_cfg)

    with open('config/visual-params.json') as fh:
        visual_cfg = json.load(fh)
    visual(**visual_cfg)

    # with open('config/example-params.json') as fh:
    #     example_cfg = json.load(fh)
    # example(**example_cfg)


    return

if __name__ == '__main__':

    targets = sys.argv[1:]
    main(targets)
