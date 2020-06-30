.PHONY: app
app: Xemime.app/Contents/MacOS/xemime.jar Xemime.app/Contents/MacOS/min-jre

Xemime.app/Contents/MacOS/xemime.jar:
	sbt assembly
	mv dist/xemime.jar Xemime.app/Contents/MacOS/xemime.jar

Xemime.app/Contents/MacOS/min-jre:
	jlink --compress=2 \
		--module-path "${JAVA_HOME}\jmods" \
		--add-modules java.scripting,java.desktop \
		--output Xemime.app/Contents/MacOS/min-jre

.PHONY: install
install: app
	cp -rf Xemime.app /Applications/Xemime.app

.PHONY: uninstall
uninstall:
	rm -rf /Applications/Xemime.app

.PHONY: clean
clean:
	rm -rf Xemime.app/Contents/MacOS/min-jre
	rm -f Xemime.app/Contents/MacOS/xemime.jar
