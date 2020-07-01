#!/bin/sh -e

SCRIPT_DIR=$(cd "$(dirname "${0}")"; pwd)
"$SCRIPT_DIR/min-jre/bin/java" -jar "$SCRIPT_DIR/xemime.jar"
