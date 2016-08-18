/**
 * Created by ariel7342 on 8/18/2016.
 */
var app = angular.module('app', []);
app.controller('myCtrl', function($scope) {
    $scope.messages = [];
    var source = new EventSource("/sse/stream");
    source.addEventListener('message',function (e) {
        $scope.messages.push(JSON.parse(e.data));
    });
    $scope.sendMsg = function (msg) {
        $.get("/sse/message/" + msg, function(data, status){
             console.log("Data: " + data + "\nStatus: " + status);
        });
    }
});