#!/usr/bin/env bash

# #########################
# ## Node Entrypoint ######
# #########################

tmux -2 new-session -d -s bookit
tmux rename-window -t bookit:0 'react'
tmux select-window -t bookit:0

tmux new-window -t bookit
tmux rename-window -t bookit:1 'back'
tmux select-window -t bookit:1

tmux select-window -t bookit:0
tmux -2 attach-session -t bookit
