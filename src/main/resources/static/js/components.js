$( document ).ready(function() {
    $('.parallax').parallax();


    $(".dropdown-trigger").dropdown();

    $('select').formSelect();

    $('#url').change(function (e){
        $("#carregarImg").attr("src", $(this).val());
    });

    $("#carregarImg").attr("src", $("#url").val());

    $('#email').keyup(function(e){
        if(e.keyCode == 13)
        {
            window.location.href="/admin/console/user/" + this.value;
        }
    });

    $('#email2').keyup(function(e){
        if(e.keyCode == 13)
        {
            window.location.href="/admin/console/loan/" + this.value;
        }
    });

    $('.modal').modal();
});

function mudarCheck(id){
    console.log(id)
}