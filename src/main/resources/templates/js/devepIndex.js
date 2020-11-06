function submitReq(){
    $('#myModal').modal('show');
    $('#myModal').show();
};
function showPotfolioPic(obj){
    var imgs = obj.getElementsByTagName("img");
    source = imgs[0].src
    $("#ShowImage_Form").find("#img_show").html("<image src='"+source+"' class='carousel-inner img-responsive img-rounded' />");
    $("#ShowImage_Form").modal()
};



$(function(){ 
    var selectItems=[];
});
   
   
