#!/usr/bin/env python3
"""Build and test using isolated temporary data, never touching real Entitaeten.ser."""
from pathlib import Path
import os,shutil,subprocess,tempfile
root=Path(__file__).resolve().parents[1]
java=str(Path(os.environ['JAVA_HOME'])/'bin/java') if os.environ.get('JAVA_HOME') else shutil.which('java')
if not java:raise SystemExit('JDK 17+ erforderlich (JAVA_HOME oder PATH).')
with tempfile.TemporaryDirectory(prefix='test-',dir=root) as folder:
    folder=Path(folder);build=folder/'build';build.mkdir()
    sources=sorted((root/'fortbildungsverwaltung').glob('*.java'))+sorted((root/'tests/fortbildungsverwaltung').glob('*.java'))
    subprocess.run([java,'-m','jdk.compiler/com.sun.tools.javac.Main','-encoding','UTF-8','-Xlint:all','-d',str(build),*map(str,sources)],check=True)
    for name in ['RegressionTest','ServiceTest']:
        work=folder/name;work.mkdir()
        subprocess.run([java,'-cp',str(build),'fortbildungsverwaltung.'+name],cwd=work,check=True)
    work=folder/'console';work.mkdir()
    def run(lines):
        r=subprocess.run([java,'-cp',str(build),'fortbildungsverwaltung.FortbildungsverwaltungHS'],cwd=work,input='\n'.join(lines)+'\n',capture_output=True,text=True,timeout=15)
        assert r.returncode==0,r.stderr
        assert 'Exception' not in r.stdout,r.stdout
        return r.stdout
    out=run(['admin','aaAA11&&aa','1','1','Otto','aaAA11&&aa','2','1','Susi','aaAA11&&aa','1','7','2'])
    assert out.count('Sachbearbeiter erfasst.')==2
    out=run(['Otto','aaAA11&&aa','1','2']);assert 'keine Berechtigung als Administrator' in out
    out=run(['Otto','falsch','2','2']);assert 'Login ist leider fehlgeschlagen' in out
    out=run(['Susi','aaAA11&&aa','2','1','1','Otto_neu','','5','2'])
    assert 'Sachbearbeiter geändert: Otto_neu' in out and 'Sachbearbeiter erfassen' not in out
    out=run(['admin','aaAA11&&aa','1','2','1','1','7','2'])
    assert 'wirklich gelöscht' in out and 'Sachbearbeiter gelöscht.' not in out
    out=run(['Otto_neu','aaAA11&&aa','2','5','2']);assert 'Login ist leider fehlgeschlagen' not in out
    out=run([]);assert 'Eingabe beendet.' in out
    print('7 console scenarios passed.')
