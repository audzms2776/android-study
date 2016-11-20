/**
 * Created by TT on 2016-11-20.
 */
const express = require('express');
const app = express();
var FCM = require('fcm').FCM;
var apiKey = 'API Key';
var fcm = new FCM(apiKey);
var ids = [];

app.get('/register', (req, res)=> {

    var id = req.query['id'];
    console.log(id);

    if (ids.indexOf(id) == -1) {
        ids.push(id);
    }

    res.send({msg: ids});
});

app.get('/messaging', (req, res)=> {

    var target = ids[req.query['target']];
    console.log(target);

    var message = {
        registration_id: target, // required
        collapse_key: 'Collapse key',
        data1: 'this is data1 war !',
        data2: 'this is data2 war !'
    };

    fcm.send(message, function (err, messageId) {
        if (err) {
            console.log("Something has gone wrong!");
        } else {
            console.log("Sent with message ID: ", messageId);
            res.send({'msg': '099999'});
        }
    });
});

app.listen(3000, ()=> {
    console.log('server 3000 start');
});