function percentToVH(percent) {
  var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);
  return (percent * h) / 100;
}

function percentToVW(percent) {
  var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
  return (percent * w) / 100;
}

function remToPixel(rem) {    
    return rem * parseFloat(getComputedStyle(document.documentElement).fontSize);
}

export {percentToVH, percentToVW, remToPixel};