[Setup]
AppName=Xemime
AppVersion=0.1.0 Pre-Alpha
VersionInfoVersion=0.1.0.0
VersionInfoDescription=プログラミング言語 Xemime の実行環境をインストールするプログラム
AppCopyright=Kodai Matsumoto
DefaultDirName={pf}\Xemime
AppendDefaultDirName=no
OutputBaseFilename=xemime_v2_setup

[Languages]
Name: japanese; MessagesFile: compiler:Languages\Japanese.isl

[Files]
Source: "..\dist\min-jre\*"; DestDir: "{app}\min-jre"; Flags: ignoreversion recursesubdirs
Source: "..\dist\xemime.jar"; DestDir: "{app}"; Flags: ignoreversion
Source: "launch.bat"; DestDir: "{app}"; Flags: ignoreversion

[Tasks]
Name: desktopicon; Description: "デスクトップにショートカットアイコンを作成する";

[Icons]
Name: "{commondesktop}\Xemime"; Comment: "Xemime 言語インタプリタ"; Filename: "{app}\launch.bat"; WorkingDir: "{app}"; Tasks: desktopicon
