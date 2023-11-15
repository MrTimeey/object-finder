package io.github.mrtimeey.objectfinder.type;
record Path(String path) {
   static Path of(String path){
      return new Path(path);
   }
}
