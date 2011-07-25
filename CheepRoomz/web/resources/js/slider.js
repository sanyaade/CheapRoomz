// JavaScript Document
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