var unique = (function(n) {
    return function() {
      n += 1;
      return n;
    }
  }(0));

export default unique;