// JavaScript Document

//Image slider
jQuery(document).ready(function() {
	var curIndex = 0;
	var bMoving = false;
	$('.SlideLeft').click(function() {
		if (bMoving) return;
		bMoving = true;
		var cnt = $('.SlideContent').length;
		$('.SlideContent:eq('+curIndex+')').fadeOut( 500, function(){
			curIndex = (curIndex - 1 + cnt) % cnt;
			$('.SlideContent:eq('+curIndex+')').fadeIn( 500, function(){
				bMoving = false;
			});
		});
	});
	
	$('.SlideRight').click(function() {
		if (bMoving) return;
		bMoving = true;
		var cnt = $('.SlideContent').length;
		$('.SlideContent:eq('+curIndex+')').fadeOut( 500, function(){
			curIndex = (curIndex + 1) % cnt;
			$('.SlideContent:eq('+curIndex+')').fadeIn( 500, function(){
				bMoving = false;
			});
		});
	});
});

//Rating
jQuery(document).ready(function() {
	$('.ResultTable .Rating').mouseup(function(event) {
		var d = $(this).width() / 5;
		var r = Math.ceil((event.pageX - $(this).offset().left) / d);
		var wid = r * 20;
		$(this).find('.Percent').css("width", wid + "%");
	});
	$('.ResultTable .Rating').mouseleave(function(event) {
		//if (event.pageX < $(this).offset().left)
		//	$(this).find('.Percent').css("width", "0%");
	});
	$('.ResultTable .Rating').mousemove(function(event) {
	});
});

jQuery(document).ready(function() {
	$('.RoomDetailsWrapper .tabs li').click(function() {
		$('.RoomDetailsWrapper .TabContent').hide();
		$('.RoomDetailsWrapper .tabs li').removeClass('selected');
		$('.RoomDetailsWrapper .tabs li#' + this.id).addClass('selected');
		var id = (this.id).substring(0, this.id.length - 4);
		$('.RoomDetailsWrapper #' + id).show();
	});
});