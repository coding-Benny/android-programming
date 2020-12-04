import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:shared_preferences/shared_preferences.dart';

class AppState extends ChangeNotifier {
  int state = 0;

  AppState() {
    SharedPreferences.getInstance().then((prefs) {
      state = prefs.getInt('state') ?? 0;
    }
    );
  }

  void increaseState() {
    state++;
    SharedPreferences.getInstance().then((prefs) =>
        prefs.setInt('state', state)
    );
    notifyListeners();
  }
}

void main() => runApp(
  ChangeNotifierProvider(
      create: (context) => AppState(),
      child: MyApp()
  )
);

class StartPage extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Start Page')),
      body: Center(
          child: RaisedButton(
            child: Text("Go to NextPage"),
            onPressed: () {
              Provider.of<AppState>(context).increaseState();
              Navigator.pushNamed(context, '/next');
            }
          )
      ),
    );
  }
}

class NextPage extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Next Page')),
      body: Center(
        child: Consumer<AppState>(
          builder: (context, appState, child) => RaisedButton(
            child: Text("Go Back ${appState.state}"),
              onPressed: () => Navigator.pop(context),
          )
        )
      ),
    );
  }
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return MaterialApp(
      title: 'Navigation Test',
      initialRoute: '/',
      routes: {
        '/': (context) => StartPage(),
        '/next': (context) => NextPage(),
      },
    );
  }
}
