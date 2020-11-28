import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';

void main() => runApp(MyApp());

class _MyFormState extends State<MyForm> {
  TextEditingController textEditingController = TextEditingController();
  String displayedText = 'Hello, Flutter';

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text(displayedText),
          TextField(controller: textEditingController),
          RaisedButton(
            child: Text("Change Text!"),
            onPressed: () => setState(() {
              displayedText = textEditingController.text;
            }),
          )
        ],
      )
    );
  }
}

class MyForm extends StatefulWidget {
  @override
  _MyFormState createState() => _MyFormState();
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Button and TextField Test',
      home: Scaffold(appBar: AppBar(title: Text('AppBar Title')), body: MyForm()),
    );
  }
}
