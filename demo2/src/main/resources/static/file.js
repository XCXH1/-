$(function (){
    $("#file").change(function (){
        // $("form")[0].files[0]：标签选择器，选择第0个
        var file = $("#file")[0].files[0];
        var formdata = new FormData();
        formdata.append("file",file);
        $.ajax({
            url:"http://localhost:8080/upload",
            type:"post",
            data:formdata,
            processData: false,
            /*请求头*/
            contentType:false,
            success:function (res){
                // 将 res.img 的值设置为 <img> 标签的 src 属性值。
                $("#img").attr("src",res.img);
                /*$("#file1").value = res.img;*/
                $("#img").attr("hidden",false);

                $("#file1").val(res.img);
            },error:function (res){

            }
        })
    })
})