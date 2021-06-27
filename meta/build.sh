hg clone https://hg.sr.ht/~duangle/scopes/
cd scopes
wget https://github.com/llvm/llvm-project/releases/download/llvmorg-12.0.0/clang+llvm-12.0.0-x86_64-linux-gnu-ubuntu-20.04.tar.xz
tar -xvf clang+llvm-12.0.0-x86_64-linux-gnu-ubuntu-20.04.tar.xz
rm clang+llvm-12.0.0-x86_64-linux-gnu-ubuntu-20.04.tar.xz
mv clang+llvm-12.0.0-x86_64-linux-gnu-ubuntu-20.04 clang
git clone https://github.com/KhronosGroup/SPIRV-Tools.git   spirv-tools
git clone https://github.com/KhronosGroup/SPIRV-Headers.git spirv-tools/external/spirv-headers
git clone https://github.com/google/googletest.git          spirv-tools/external/googletest
git clone https://github.com/google/effcee.git              spirv-tools/external/effcee
git clone https://github.com/google/re2.git                 spirv-tools/external/re2
mv spirv-tools SPIRV-Tools
git clone https://github.com/KhronosGroup/SPIRV-Cross
cd SPIRV-Tools
mkdir build && cd build
cmake -G "Unix Makefiles" ..
cd ../../
wget https://github.com/bkaradzic/bx/raw/master/tools/bin/linux/genie
chmod +x genie
./genie gmake
make -C build config=release -j2
cp clang/lib/clang/12.0.0/include lib/clang/include
